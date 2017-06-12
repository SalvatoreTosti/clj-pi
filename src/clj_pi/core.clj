(ns clj-pi.core
  (:gen-class))

(defn sum-component
  [i numer denom-add]
   (with-precision (+ i 10) (/ numer (+ denom-add (* 8 i)))))

(defn sub-component
  [i]
    (-> (sum-component i 4M 1)
        (- (sum-component i 2M 4))
        (- (sum-component i 1M 5))
        (- (sum-component i 1M 6))))

(defn pi-nth
  [n]
  (-> (reduce + (map (fn [i]  (with-precision (+ i 10) (*
                                                      (with-precision (+ i 10) (/ 1  (with-precision (+ i 10) (.pow (bigdec 16) i))))
                                                      (sub-component i)))) (range 0 100)))
    (str)
    (subs 2)
    (#(str "3" %))
    (nth (dec n))
    (str)))
