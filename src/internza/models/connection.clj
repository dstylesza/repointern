(ns internza.models.connection)

;yesql uses vanilla jdbc under the hood (uses the connection map)
(def db-spec {
  :classname "org.mysql.Driver"
  :subprotocol "mysql"
  :subname "//localhost/internza"
  :user "root"
  :password "password"})

