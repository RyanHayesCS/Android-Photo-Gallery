# Android-Photo-Gallery

This project utilizes the Flickr API in order to load a gallery of the most recent Flickr uploads (whose content is not censored so tread lightly).
Originally created as means of exploring handlers, the custom handler class has since been replaced with a Picasso (http://square.github.io/picasso/) 
handler which also takes care of chacheing and preloading. 

Flickr is accessed through the FlickrFetcher class, which recieves new uploads (via an Http connection) as JSON objects. These objects are then 
parsed into GalleryItems which are presented within the PhotoGallery or PhotoPage. The PhotoGallery utilizes a handler to update the view as the user
navigates the gallery.
