(ns classifier.twitter
  (:use
    [twitter.oauth]
    [twitter.callbacks]
    [twitter.callbacks.handlers]
    [twitter.api.streaming]) 
  (:require
    [clojure.data.json :as json]
    [http.async.client :as haClient]
    [classifier.config.credentials.twitter :as twitter-credentials]
    [classifier.config.twitter :as twitter-config])
  (:import 
    (twitter.callbacks.protocols AsyncStreamingCallback)))
  
(defn start
  "Start to collect tweets"
  []
  (def social-impact-oauth-creds (make-oauth-creds (:api-key twitter-credentials/social-impact-keys) 
                                                   (:api-secret twitter-credentials/social-impact-keys)
                                                   (:access-token twitter-credentials/social-impact-keys)
                                                   (:access-token-secret twitter-credentials/social-impact-keys)))
  (def ^:dynamic 
    *collect-tweets*
    (AsyncStreamingCallback. (comp println #(:text %) json/read-json #(str %2))
                             (comp println response-return-everything)
                             exception-print))

  (statuses-filter :params {:track (clojure.string/join "," twitter-config/hashtags)}
                   :oauth-creds social-impact-oauth-creds
                   :callbacks *collect-tweets*))

