(defproject classifier "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.5.1"]
                 [twitter-api "0.7.5"] 
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]]
  :main ^:skip-aot classifier.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
