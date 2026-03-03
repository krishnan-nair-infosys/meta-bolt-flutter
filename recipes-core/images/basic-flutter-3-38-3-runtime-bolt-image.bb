SUMMARY = "Flutter runtime bolt image"

inherit base-bolt-image
IMAGE_INSTALL += "flutter-engine"
IMAGE_INSTALL += "flutter-basic-embedder"
#IMAGE_INSTALL += "flutter-auto"
