(ns elevator-simulator.model
  )

#_(defprotocol Person
  (request-elevator [dest-floor])
  )

(defrecord Person [name dest-floors])

(defrecord Floor [floor-num])

(defrecord Building [num-floors num-elevators])

(defn test_yo [] "test")
