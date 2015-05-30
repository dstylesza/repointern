(ns internza.models.educationlevel-model
  (:require [yesql.core :refer [defqueries]]
            [internza.models.connection :refer [db-spec]]
            ))

(defqueries "internza/models/sql/educationlevel.sql" {:connection db-spec})

(def get-educationlevels-memo (memoize get-educationlevels))
