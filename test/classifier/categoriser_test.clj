(ns classifier.categoriser-test
  (:require 
    [clojure.test :refer :all]
    [classifier.categoriser :refer :all]))

(deftest tokenize-test
    (is (= #{"hi" "world"} (tokenize "hi world"))))
