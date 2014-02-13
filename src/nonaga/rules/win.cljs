(ns nonaga.rules.win
  (:require [clojure.set :as s]
            [nonaga.rules.coord :as c]))

(defn all-connected [balls]
  (letfn [(connected [ball]
            (not (empty? (s/intersection balls (c/neighbours ball)))))]
    (every? connected balls)))

(defn find-winner [{:keys [red blue]}]
  (cond
   (all-connected red)  :red
   (all-connected blue) :blue))
