(ns internza.routes.admin.intern
  (:require [internza.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [clojure.java.io :as io]
            [internza.models.province-model :refer [get-provinces-memo]]
            [internza.models.user-model :refer [update-intern-user! get-intern-by-id verify-user<!] ]
            [internza.models.educationlevel-model :refer [get-educationlevels-memo] ]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [ring.util.response :refer [response redirect]]
            [internza.util.hasher :refer [unhash]]
            [noir.session :as session]
            ))



(defn set-user! []
  (session/put! :test "TEST"))


(defn edit-intern [hashed-userid req]
  (when-let [userid (unhash hashed-userid)]
    (do
    (verify-user<! {:id userid} )
    (set-user!)
    (layout/render "admin/intern_profile.html" { :provinces (get-provinces-memo)
                                                 :educationlevels (get-educationlevels-memo)
                                                 :token (anti-forgery-field)
                                                 :user (first (get-intern-by-id {:userid userid}))
                                                 }))))


(defn update-intern[form-data]
  (update-intern-user! form-data))


(defroutes admin-intern-routes
  (GET "/admin/:hashed-userid/intern" [hashed-userid :as req] (edit-intern hashed-userid req))
  (POST "/admin/intern" [& form] (update-intern form)))
