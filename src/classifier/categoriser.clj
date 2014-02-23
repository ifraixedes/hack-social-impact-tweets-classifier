(ns classifier.categoriser
  (:require 
    [classifier.config.categories :as cats]
    [clojure.core.async :as async :refer :all]
    ))

(defn filtering
  [channel-in]
  (thread (while true
    (println (<!! channel-in)))))

(defn matched-categories
  [words categories]
  (loop [categories]
    (if (= 0 (count categories))
      []
      (let [category (categories 0)]
        (if (match-to-this-category words category)
          (conj (recur (pop categories)) (:name category))
          (recur (pop categories)))))))

(defn- match-to-this-category
  [words category]
  (if (= 0 (count (clojure.set/intersection words (:keywords category))))
    false
    true)) 

(defn- tokenize
  [text]
  (set (clojure.string/split text #"\s|\.|,")))

