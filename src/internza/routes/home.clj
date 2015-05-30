(ns internza.routes.home
  (:require [internza.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [clojure.java.io :as io]
            [internza.models.province-model :refer [get-provinces-memo]]
            [internza.models.city-model :refer [get-cities-memo get-cities-by-provinceid-memo]]
            [internza.models.user-model :refer [add-intern-user! update-intern-user! get-intern-by-id] ]
            [internza.validators.user-validator :refer [validate-user-signup]]
            [internza.models.educationlevel-model :refer [get-educationlevels-memo] ]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [ring.util.response :refer [response redirect]]
            [internza.util.hasher :refer [hasher]]
            [ring.handler.dump :refer [handle-dump]]))

(defn home-page []
    (layout/render
    "home.html" {:view "show-login"
                 :docs (-> "docs/docs.md" io/resource slurp)
                 :educationlevels (get-educationlevels-memo)
                 :token (anti-forgery-field)}))

(defn about-page []
  (layout/render "about.html"))


(defn signup-user [form-data]
  (if-let[errors (validate-user-signup form-data)]
    (if (empty? errors)
      (layout/render "home.html" (assoc form-data
                                   :token (anti-forgery-field)
                                   :educationlevels (get-educationlevels-memo)
                                   :view "login-complete"
                                   :newuserlink (hasher (:generated_key (add-intern-user! form-data)))
                                   ))
      (layout/render "home.html" (assoc form-data
                                   :errors errors
                                   :token (anti-forgery-field)
                                   :educationlevels (get-educationlevels-memo)
                                   :view "show-login"
                                   )))))


(defn get-cities-by-provinceid [provinceid]
   (get-cities-by-provinceid-memo {:provinceid provinceid}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (POST "/" [& form] (signup-user form))
  (GET "/:provinceid/getcities" [provinceid] (get-cities-by-provinceid provinceid))
  (GET "/request" [] handle-dump))


















