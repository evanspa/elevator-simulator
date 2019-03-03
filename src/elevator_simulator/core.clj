(ns elevator-simulator.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
