(ns extjs-macs.core)

(defmacro on-ready [& body]
  `(.onReady extjs4.core/ext
            (fn []
              ~@body)))

(defmacro extend-ext
  "Used to extend an ExtJS4 class

name
  The name of the class to extend
config
  The configuration for the class.  Don't forget to specify extends
methods
  The methods to override in the class"
  [name config & methods]
  `(extjs4.core/define ~name
     ~(reduce (fn [config method]
                (assoc config (keyword (first method))
                       (let [this-name (first (second method))
                             params (vec (rest (second method)))
                             body (drop 2 method)]
                         `(fn ~params
                            (cljs.core/this-as ~this-name ~@body)))
                           )) config methods)))
