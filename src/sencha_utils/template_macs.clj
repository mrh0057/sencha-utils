(ns sencha-utils.template-macs
  (:use hiccup.core))

(defn- create-extjs-var [symbol]
  (str "{" (name symbol) "}"))

(defn- change-vars [html-vec]
  (vec
   (map #(cond (vector? %)
               (change-vars %)
               (symbol? %)
               (create-extjs-var %)
               (map? %)
               (reduce (fn [map pair]
                         (assoc map
                           (first pair)
                           (if (symbol? (second pair))
                             (create-extjs-var (second pair))
                             (second pair)))) {} %)
               :else
               %) html-vec)))

(defmacro create-html
  "Used to create html from a clojure vectory using the hiccup library"
  [html-vec]
  (html (change-vars html-vec)))
