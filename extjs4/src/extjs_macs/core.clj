(ns extjs-macs.core)

(defmacro on-ready [& body]
  `(.onReady extjs4.core/ext
            (fn []
              ~@body)))

(defn- update-config [config base]
  (assoc config :extends (name base)))

(defmacro extend-ext
  "Used to extend an ExtJS4 class

clazz
  The name of the class to extend
base
  The base class
additional-props
  The optional config to pass to the define
methods
  The methods to override"
  [clazz base & options]
  (let [clazz (name clazz)
        config (update-config
                (if (map? (first options))
                  (first options)
                  {}) base)
        methods (if (map? (first options))
                  (rest options)
                  options)]
    `(extjs4.core/define ~clazz
       ~(reduce (fn [config method]
                  (assoc config (keyword (first method))
                         (let [this-name (first (second method))
                               params (vec (rest (second method)))
                               body (drop 2 method)]
                           `(fn ~params
                              (cljs.core/this-as ~this-name ~@body)))))
                config methods))))
