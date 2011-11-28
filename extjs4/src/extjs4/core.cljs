(ns extjs4.core
  (:require ;[clojure.browser.repl :as repl]
            [sencha-utils.core :as sencha])
  (:require-macros [extjs-macs.core :as core-macs]))

;(repl/connect "http://localhost:9000/repl")

(def ext (js* "Ext"))

(defn create [clazz config]
  (.create ext (name clazz) (sencha/to-config config)))

(defn application [name launch-func]
  (.application ext (sencha/to-config
                     {:name  name
                      :launch launch-func})))

(defn define [clazz config]
  (.define ext (name clazz) (sencha/to-config config)))

(defn call-parent [this & args]
  (.callParent this (sencha/convert-array args)))
