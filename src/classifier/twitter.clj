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
    [classifier.config.twitter :as twitter-config]
    [clojure.core.async :as async :refer :all])
  (:import 
    (twitter.callbacks.protocols AsyncStreamingCallback)))
  
(defn collect
 "Collect tweets and send them to channel"
  []
  (let [channel (chan)]
    (thread (
             def social-impact-oauth-creds (make-oauth-creds (:api-key twitter-credentials/social-impact-keys) 
                                                             (:api-secret twitter-credentials/social-impact-keys)
                                                             (:access-token twitter-credentials/social-impact-keys)
                                                             (:access-token-secret twitter-credentials/social-impact-keys)))
            (def ^:dynamic 
              *collect-tweets*
              (AsyncStreamingCallback. (comp (fn [tweet] (go (>! channel (:text tweet)))) json/read-json #(str %2))
                                       (comp println response-return-everything)
                                       exception-print))

            (statuses-filter :params {:track (clojure.string/join "," twitter-config/hashtags)}
                             :oauth-creds social-impact-oauth-creds
                             :callbacks *collect-tweets*))
    channel))

