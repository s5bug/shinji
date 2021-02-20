package tf.bug.shinji

trait Symmetric[Obj, Hom[_ <: Obj, _ <: Obj], I <: Obj, Tens[_ <: Obj, _ <: Obj] <: Obj] extends Monoidal[Obj, Hom, I, Tens] {
  def swap[A <: Obj, B <: Obj]: Hom[Tens[A, B], Tens[B, A]]

  override def associateLeft[A <: Obj, B <: Obj, C <: Obj]: Hom[Tens[A, Tens[B, C]], Tens[Tens[A, B], C]] = {
    val step1 = swap[A, Tens[B, C]]
    val step2 = associateRight[B, C, A]
    val step3 = swap[B, Tens[C, A]]
    val step4 = associateRight[C, A, B]
    val step5 = swap[C, Tens[A, B]]

    val asRight = associateRight[A, B, C]

    val after = andThen(asRight, andThen(step1, andThen(step2, andThen(step3, andThen(step4, step5)))))
    val before = compose(after, compose(step5, compose(step4, compose(step3, compose(step2, step1)))))

    before
  }

  override def unitorRight[A <: Obj]: Hom[Tens[A, I], A] =
    andThen(swap[A, I], unitorLeft[A])

  override def deunitorRight[A <: Obj]: Hom[A, Tens[A, I]] =
    andThen(deunitorLeft[A], swap[I, A])

}
