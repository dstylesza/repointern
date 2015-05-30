(ns internza.models.user-model
  (:require [yesql.core :refer [defqueries]]
            [internza.models.connection :refer [db-spec]]
            [crypto.password.bcrypt :as password]
            [internza.models.intern-model :as intern]
            ))

(defqueries "internza/models/sql/user.sql" {:connection db-spec})

(defn add-intern-user! [user]
  "Saves User To Database"
  (let [user (assoc user :internid (:generated_key (intern/insert-educationlevel<! user)))
        new-user (->>
                  (password/encrypt (:password user))
                  (assoc user :password)
                  insert-intern-user<!)]
      (dissoc new-user :password)))

(defn update-intern-user! [intern]
  (print-str intern))




