(ns elevator-simulator.core
  (:require [elevator-simulator.model :as model]
            [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]]))

(def elevator-1 {:direction :up
                 :destinations []
                 :people []})

(def sim-1 {:building {:num-floors 13
                       :num-elevators 4}
            :people
           })


(defn start-simulation
  [num-floors num-elevators]

  "a"

  )

(defn indexed
  [coll]
  (map vector (iterate inc 0) coll))

(defn index-filter
  [pred coll]
  (when pred
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))
