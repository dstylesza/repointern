(ns internza.models.city-model
  (:require [yesql.core :refer [defqueries]]
            [internza.models.connection :refer [db-spec]]
            ))

(defqueries "internza/models/sql/city.sql" {:connection db-spec})

(def get-cities-memo (memoize get-cities))

(def get-cities-by-provinceid-memo (memoize get-cities-by-provinceid))

;THE PRECEDING CODE GENERATES FUNCTIONS FOR THE USERS.SQL FILE


