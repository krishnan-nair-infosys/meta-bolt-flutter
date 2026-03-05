SUMMARY = "Flutter runtime bolt image  with liberty global basic wayland embedder"
# flutter-engine from https://github.com/meta-flutter/meta-flutter/tree/master/recipes-graphics/flutter-engine
# Liberty Global basic wayland embedder https://github.com/LibertyGlobal/flutter-embedder-wayland
# status 5 March 2026 by BartC : is compiling successfully but runtine issues
# the basic wayland embedder source code needs to be adapted to work with the different app bundle dir structure used in meta-flutter
 
inherit base-bolt-image

IMAGE_INSTALL += "flutter-engine"
IMAGE_INSTALL += "flutter-basic-embedder"
