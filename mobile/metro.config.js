const { getDefaultConfig } = require('expo/metro-config');

const config = getDefaultConfig(__dirname);

// Bloquer les fichiers de test
config.resolver.blockList = [/__tests__\/.*/, /.*\.test\.js/, /.*\.spec\.js/];

module.exports = config;
