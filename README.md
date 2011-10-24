# Sencha Utils

Contains functions for working with ExtJS libraries.

## Converting Clojure Map to an ExtJS/Sencha config object

`to-config` converts a clojure map into a config object.

* The function converts any clojure maps to JavaScript Objects
* Converts any vectors or lists into JavaScript arrays if they are
values of the map
* When processing the array it also converts any lists/vectors and
maps to JavaScript arrays and objects.

    (to-config clj-map)
