SUMMARY = "Flutter runtime bolt image"
SUMMARY = "Flutter runtime bolt image  with AGL toyota flutter-auto wayland embedder"
# flutter-engine from https://github.com/meta-flutter/meta-flutter/tree/master/recipes-graphics/flutter-engine
# flutter-auto embedder from https://github.com/meta-flutter/meta-flutter/blob/master/recipes-graphics/toyota/flutter-auto_2.0.bb
# status 5 March 2026 by BartC : is compiling successfully, no runtime tests done yet
# embedder not adapted yet to support simple-shell wayland extension protocol used in RDK, is specific to westeros compositor

inherit base-bolt-image
IMAGE_INSTALL += "flutter-engine"
IMAGE_INSTALL += "flutter-auto"
