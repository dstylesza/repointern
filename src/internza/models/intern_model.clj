(ns internza.models.intern-model
  (:require [yesql.core :refer [defqueries]]
            [internza.models.connection :refer [db-spec]]
            ))

(defqueries "internza/models/sql/intern.sql" {:connection db-spec})


