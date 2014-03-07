(ns classifier.categoriser
  (:refer-clojure :exclude [map reduce into partition partition-by take merge])
  (:require
    [clojure.core.async :as async :refer :all]
    [classifier.config.categories :as categoriesSpec]))

(defn- match-to-this-category
  [words category]
  (if (= 0 (count (clojure.set/intersection words (:keywords category))))
    false
    true))

(defn tokenize
  [text]
  (disj (set (clojure.string/split text #"\s|\.|,")) ""))

(defn filtering
  [channel-in]
  (thread (while true
    (println (<!! channel-in)))))

(defn matched-categories
  [words categories]
  (defn iter
    [categoriesLeft categories]
    (if (= 0 (count categoriesLeft))
      []
      (let [category (categoriesLeft 0)]
        (if (match-to-this-category words category)
          (conj (iter (pop categoriesLeft)) (:name category))
          (iter (pop categoriesLeft)))))))
