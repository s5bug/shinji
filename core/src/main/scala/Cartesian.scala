package tf.bug.shinji

/**
 * A Cartesian Category.
 *
 * @tparam Obj The upper bound type.
 * @tparam Hom The homset functor.
 * @tparam Empty The unit object, or the empty product.
 * @tparam Prod The product functor.
 */
trait Cartesian[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  Empty <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj
] extends Symmetric[Obj, Hom, Empty, Prod] {
  /**
   * Duplicates an object.
   *
   * @tparam A The target object.
   * @return A morphism that duplicates the target object.
   */
  def duplicate[A <: Obj]: Hom[A, Prod[A, A]]

  /**
   * Terminates an object.
   *
   * @tparam A The target object.
   * @return A morphism that terminates the target object.
   */
  def terminate[A <: Obj]: Hom[A, Empty]
}
