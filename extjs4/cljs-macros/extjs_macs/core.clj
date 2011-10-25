(ns extjs-macs.core)

(defmacro expand-onready [& body]
  `(.onReady extjs4.core/ext
            (fn []
              ~@body)))
