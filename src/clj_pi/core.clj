(ns clj-pi.core
  (:gen-class))

(defn sum-component
  [precision i numer denom-add]
   (with-precision precision (/ numer (+ denom-add (* 8 i)))))

(defn sub-component
  [precision i]
    (-> (sum-component precision i 4M 1)
        (- (sum-component precision i 2M 4))
        (- (sum-component precision i 1M 5))
        (- (sum-component precision i 1M 6))))

(defn pi-nth
  [n]
  (let [precision (+ n 10)]
    (-> (reduce + (map (fn [i]
                         (*
                          (/ 1M  (.pow 16M i))
                          (sub-component precision i)))
                       (range 0 precision)))
        (str)
        (subs 2)
        (#(str "3" %))
        (nth (dec n))
        (str))))
