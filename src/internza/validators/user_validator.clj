(ns internza.validators.user-validator
  (:require [validateur.validation :refer :all]
            [internza.models.user-model :refer :all]
            [noir.validation :as v]
            ))


(defn new? [user]
  (nil? (:id user)))


(defn unique-email? [user]
  (if-let [found-users (get-user-by-email {:email (:email user)})]
    (= (:id user) (:id (first found-users)))
    (true)))

(defn educationlevel-selected? [user]
  (print user)
  (= true false))


(def email-validator (validation-set
                       (validate-with-predicate :email
                         #(v/is-email? (:email %))
                         :message-fn (fn [validation-map]
                                       (if (v/has-value? (:email validation-map))
                                             "The email format is incorrect"
                                             "Required")))
                      (validate-with-predicate :email
                         #(unique-email? %)
                         :message-fn (fn [validation-map]
                                       (when (v/has-value? (:email validation-map))
                                         "This email address has already been used")))))

(def name-validator
  (validation-set (validate-with-predicate :name
                                           #(v/has-value? (:name %))
                                           :message "Required")))

(def educationlevel-validator
  (validation-set (validate-with-predicate :educationlevelid
                                           #(v/has-value? (:educationlevelid %))
                                           :message "Please select your education level")))

(def password-validator
  (validation-set (validate-with-predicate :password
                                           #(v/has-value? (:password %))
                                           :message "Required")))


(defn validate-user-signup [signup]
  "Validates the incoming map of values from our signup form,
   and returns a set of error messages for any invalid key."
  (let [v (compose-sets email-validator educationlevel-validator password-validator name-validator)]
    (v signup)))
