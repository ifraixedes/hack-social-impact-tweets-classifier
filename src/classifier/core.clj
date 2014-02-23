(ns classifier.core
  (:gen-class)
  (:require 
    [classifier.twitter :as twitter]
    [classifier.categoriser :as classifier]))

(defn -main
  "Orchestrate everything to make it happens"
  [& args]
  (println "Let's go!!")
  (classifier/filtering (twitter/collect))
  (Thread/sleep 10000))
 

