(ns internza.util.hasher
  (:require [hashids.core :as h] ))


;Set Options for the hash
(def hashids-opts {:salt "salthash" :min-length 7})


(defn hasher [data]
  (h/encode hashids-opts data))

(defn unhash [data]
  (let [decoded-data (h/decode hashids-opts data)]
    (if (empty? decoded-data)
    nil
    decoded-data)))

