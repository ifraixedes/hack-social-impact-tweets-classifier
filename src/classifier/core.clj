(ns classifier.core
  (:gen-class)
  (:use
    [twitter.oauth]
    [twitter.callbacks]
    [twitter.callbacks.handlers]
    [twitter.api.restful]
    [classifier.config.credentials twitter])
  (:import 
    (twitter.callbacks.protocols SyncSingleCallback)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (def social-impact-oauth-creds (make-oauth-creds (:api-key social-impact-keys) 
                                                   (:api-secret social-impact-keys)
                                                   (:access-token social-impact-keys)
                                                   (:access-token-secret social-impact-keys)))
  (println (users-show :oauth-creds social-impact-oauth-creds 
                           :callbacks (SyncSingleCallback. response-return-body 
                                                response-throw-error
                                                exception-rethrow)
                           :params {:screen-name "ifraixedes"})))
