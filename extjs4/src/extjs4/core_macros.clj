(ns extjs4-macs.core)

(defmacro expand-onready [& body]
  (dotimes [a (range 0 100)]
    `("a"))
  ~@body)



