(ns internza.models.province-model
  (:require [yesql.core :refer [defqueries]]
            [internza.models.connection :refer [db-spec]]
            ))

(defqueries "internza/models/sql/province.sql" {:connection db-spec})

(def get-provinces-memo (memoize get-provinces))

;THE PRECEDING CODE GENERATES FUNCTIONS FOR THE USERS.SQL FILE
