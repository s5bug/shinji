package tf.bug.shinji
package instances

object Scal extends CartesianClosedCategory[
  /* Obj = */ Any,
  /* Val = */ λ[α => Unit],
  /* Hom = */ Function1,
  /* I = */ Unit,
  /* Prod = */ Tuple2,
  /* Exp = */ Function1,
] {

  override def id[A <: Any](v: Unit): A => A =
    (a: A) => a

  override def compose[A <: Any, B <: Any, C <: Any](f: A => B, g: B => C): A => C =
    (a: A) => g(f(a))

  override def homExtractLeft[A <: Any, B <: Any](hom: A => B): Unit =
    ()

  override def homExtractRight[A <: Any, B <: Any](hom: A => B): Unit =
    ()

  override val monoidalTensBifunctor: Bifunctor[Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Tuple2] =
    new Bifunctor[Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Tuple2] {
      override val functorCategoryInLeft: Category[Any,  λ[α => Unit], Function1] = Scal
      override val functorCategoryInRight: Category[Any, λ[α => Unit], Function1] = Scal
      override val functorCategoryOut: Category[Any, λ[α => Unit], Function1] = Scal

      override def mapVals[A <: Any, B <: Any](va: Unit, vb: Unit): Unit = ()

      override def bimap[A <: Any, B <: Any, C <: Any, D <: Any](l: A => C, r: B => D): ((A, B)) => (C, D) =
        { case (a, b) => (l(a), r(b)) }
    }

  override def tensExtractLeft[A <: Any, B <: Any](v: Unit): Unit = ()
  override def tensExtractRight[A <: Any, B <: Any](v: Unit): Unit = ()

  override def identity: Unit = ()
  override def identityVal: Unit = ()

  override def associative[A <: Any, B <: Any, C <: Any]: Iso[Any, λ[α => Unit], Function1, ((A, B), C), (A, (B, C))] =
    Iso[Any, λ[α => Unit], Function1, ((A, B), C), (A, (B, C))](
      forward = { case ((a, b), c) => (a, (b, c)) },
      backward = { case (a, (b, c)) => ((a, b), c) }
    )

  override def leftUnit[A <: Any]: Iso[Any, λ[α => Unit], Function1, (Unit, A), A] =
    Iso[Any, λ[α => Unit], Function1, (Unit, A), A](
      forward = { case ((), a) => a },
      backward = { a => ((), a) }
    )

  override def rightUnit[A <: Any]: Iso[Any, λ[α => Unit], Function1, (A, Unit), A] =
    Iso[Any, λ[α => Unit], Function1, (A, Unit), A](
      forward = { case (a, ()) => a },
      backward = { a => (a, ()) }
    )

  override def braid[X <: Any, Y <: Any]: Iso[Any, λ[α => Unit], Function, (X, Y), (Y, X)] =
    Iso(_.swap, _.swap)

  override def duplicate[X <: Any](v: Unit): X => (X, X) =
    x => (x, x)

  override def terminate[X <: Any](v: Unit): X => Unit =
    _ => ()

  override val closedExpProfunctor: Profunctor[Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Function1] =
    new Profunctor[Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Function1] {
      override val functorCategoryInLeft: Category[Any, λ[α => Unit], Function1] = Scal
      override val functorCategoryInRight: Category[Any, λ[α => Unit], Function1] = Scal
      override val functorCategoryOut: Category[Any, λ[α => Unit], Function1] = Scal

      override def mapVals[A <: Any, B <: Any](va: Unit, vb: Unit): Unit = ()

      override def dimap[A <: Any, B <: Any, C <: Any, D <: Any](l: C => A, r: B => D): (A => B) => (C => D) =
        (f: A => B) => (c: C) => r(f(l(c)))
    }

  override def expExtractLeft[A <: Any, B <: Any](v: Unit): Unit = ()
  override def expExtractRight[A <: Any, B <: Any](v: Unit): Unit = ()

  override def jd[A <: Any](v: Unit): Unit => A => A =
    id

  override def read[X <: Any, Y <: Any, Z <: Any](vx: Unit, vy: Unit, vz: Unit): (Y => Z) => (X => Y) => X => Z =
    f => g => compose(g, f)

  override def homTensAdj[B <: Any]: Adjunction[Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, (B, *), B => *] =
    new Adjunction[Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, (B, *), B => *] {
      override def free: Functor[Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, Tuple2[B, *]] =
        monoidalTensBifunctor.fixLeft[B](())

      override def forgetful: Functor[Any, λ[α => Unit], Function1, Any, λ[α => Unit], Function1, B => *] =
        closedExpProfunctor.fixLeft[B](())

      override def iso[I <: Any, J <: Any](va: Unit, vb: Unit): Iso[Any, λ[α => Unit], Function1, ((B, I)) => J, I => B => J] =
        Iso(
          forward = (f: ((B, I)) => J) => i => b => f((b, i)),
          backward = (f: I => B => J) => { case (i, b) => f(b)(i) }
        )
    }

  def optionT[F[_ <: Any] <: Any](m: Monad[Any, λ[α => Unit], Function1, F]): Monad[Any, λ[α => Unit], Function1, λ[α => F[Option[α]]]] =
    Monad.transform(Scal, OptionTAdjunction, PointedScal.liftMonad(m))

  def listT[F[_ <: Any] <: Any](m: Monad[Any, λ[α => Unit], Function1, F]): Monad[Any, λ[α => Unit], Function1, λ[α => F[List[α]]]] =
    Monad.transform(Scal, ListTAdjunction, MonoidScal.liftMonad(m))

  def stateT[F[_ <: Any] <: Any, S <: Any](m: Monad[Any, λ[α => Unit], Function1, F]): Monad[Any, λ[α => Unit], Function1, λ[α => S => F[(S, α)]]] =
    Monad.transform(Scal, Scal.homTensAdj[S], m)

}
