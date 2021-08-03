module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ?
        './' :
        '/',
    outputDir: '../java/src/main/resources/static'
};
