const mongoose = require('mongoose')
const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')

var Schema = mongoose.Schema;

const userSchema = new Schema({
    name: {
        type: String,
        required: true,
        trim: true
    },
    phone: {
      type: String,
      validate: {
        validator: function(v) {
          return /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/.test(v);
        },
        message: props => `${props.value} is not a valid phone number!`
      },
      required: [true, 'User phone number required']
    },
    password: {
        type: String,
        required: true,
        minLength: 7
    },
    tokens: [{
        token: {
            type: String,
            required: true
        }
    }],
    business_name: {type: String},
    business_type: {type: String},
    trade: {type: String}
})

userSchema.pre('save', async function (next) {
    // Hash the password before saving the user model
    const user = this
    if (user.isModified('password')) {
        user.password = await bcrypt.hash(user.password, 8)
    }
    next()
})

userSchema.methods.generateAuthToken = async function() {
    // Generate an auth token for the user
    const user = this
    const token = jwt.sign({_id: user._id}, process.env.JWT_KEY)
    user.tokens = user.tokens.concat({token})
    await user.save()
    return token
}

userSchema.statics.findByCredentials = async (phone, password) => {
    // Search for a user by phone and password.
    const user = await User.findOne({ phone} )
    if (!user) {
        throw new Error({ error: 'Invalid login credentials' })
    }
    const isPasswordMatch = await bcrypt.compare(password, user.password)
    if (!isPasswordMatch) {
        throw new Error({ error: 'Invalid login credentials' })
    }
    return user
}

const User = mongoose.model('User', userSchema)

module.exports = User