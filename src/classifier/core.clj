(ns classifier.core
  (:gen-class)
  (:use
   [classifier twitter])
  (:require 
    [clojure.core.async :as async :refer :all]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (let [channel (chan)]
                (thread (start channel))
                (while true (println (<!! channel)))
    ))
 

