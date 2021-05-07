package tf.bug.shinji

/**
 * A Category.
 *
 * @tparam Obj The upper bound type.
 * @tparam Hom The homset functor.
 */
trait Category[
  Obj,
  Con[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj]
] extends Compose[Obj, Con, Hom] {
  /**
   * An identity morphism.
   *
   * @tparam A The target object.
   * @return A morphism `id` such that `id ∘ f ≅ f ∘ id ≅ f`.
   */
  def id[A <: Obj](implicit a: Con[A]): Hom[A, A]
}
