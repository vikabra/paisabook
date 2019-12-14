const mongoose = require("mongoose");

mongoose.connect(process.env.MONGOLAB_URI||"mongodb://localhost:27017/Paisabook", (error) => {
  if (!error)
    console.log("Successful connection!");
  else
    console.log("Some error in connection. Cheggit: " + JSON.stringify(error, undefined, 2));  
});

// exports the configuration file to use later
module.exports = mongoose;
