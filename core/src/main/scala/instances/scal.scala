package tf.bug.shinji
package instances

object Scal extends CartesianClosed[
  /* Obj = */ Any,
  /* Con = */ λ[α => DummyImplicit],
  /* Hom = */ Function1,
  /* Empty = */ Unit,
  /* Prod = */ Tuple2,
  /* Exp = */ Function1
] with Arrow[
  /* Obj = */ Any,
  /* Con = */ λ[α => DummyImplicit],
  /* Hom = */ Function1,
  /* Empty = */ Unit,
  /* Prod = */ Tuple2,
  /* F = */ Function1
] {

  override def eval[A <: Any, B <: Any](implicit a: DummyImplicit, b: DummyImplicit): ((A => B, A)) => B = {
    case (f, a) => f(a)
  }

  override def multiply[A <: Any, B <: Any](implicit a: DummyImplicit, b: DummyImplicit): A => B => (A, B) =
    a => b => (a, b)

  override def lift[A <: Any, B <: Any](f: A => B): A => B = f

  override def strongProfunctorCategory: Monoidal[Any, λ[α => DummyImplicit], Function, Unit, Tuple2] = this

  override def first[A <: Any, B <: Any, C <: Any](f: A => B)(implicit c: DummyImplicit): ((A, C)) => (B, C) =
    { case (a, c) => (f(a), c) }

  override def second[A <: Any, B <: Any, C <: Any](f: A => B)(implicit c: DummyImplicit): ((C, A)) => (C, B) =
    { case (c, a) => (c, f(a)) }

  override def curry[A <: Any, B <: Any, C <: Any](f: ((A, B)) => C): A => B => C =
    a => b => f((a, b))

  override def uncurry[A <: Any, B <: Any, C <: Any](f: A => B => C): ((A, B)) => C =
    { case (a, b) => f(a)(b) }

  override def duplicate[A <: Any](implicit a: DummyImplicit): A => (A, A) =
    a => (a, a)

  override def terminate[A <: Any](implicit a: DummyImplicit): A => Unit =
    _ => ()

  override def swap[A <: Any, B <: Any](implicit a: DummyImplicit, b: DummyImplicit): ((A, B)) => (B, A) =
    { case (a, b) => (b, a) }

  override def id[A <: Any](implicit a: DummyImplicit): A => A =
    a => a

  override def profunctorLeftConstraint[A <: Any, B <: Any](f: A => B): DummyImplicit =
    DummyImplicit.dummyImplicit

  override def profunctorRightConstraint[A <: Any, B <: Any](f: A => B): DummyImplicit =
    DummyImplicit.dummyImplicit

  override def associateRight[A <: Any, B <: Any, C <: Any](implicit a: DummyImplicit, b: DummyImplicit, c: DummyImplicit): (((A, B), C)) => (A, (B, C)) =
    { case ((a, b), c) => (a, (b, c)) }

  override def unitorLeft[A <: Any](implicit a: DummyImplicit): ((Unit, A)) => A =
    { case ((), a) => a }

  override def deunitorLeft[A <: Any](implicit a: DummyImplicit): A => (Unit, A) =
    a => ((), a)

  override def unitorRight[A <: Any](implicit a: DummyImplicit): ((A, Unit)) => A =
    { case (a, ()) => a }

  override def deunitorRight[A <: Any](implicit a: DummyImplicit): A => (A, Unit) =
    a => (a, ())

  override implicit def unitConstraint: DummyImplicit =
    DummyImplicit.dummyImplicit

  override implicit def exponentialConstraint[A <: Any, B <: Any](implicit a: DummyImplicit, b: DummyImplicit): DummyImplicit =
    DummyImplicit.dummyImplicit

  override def expId[A <: Any](implicit a: DummyImplicit): Unit => A => A =
    { case () => a => a }

  override def expPoint[A <: Any](implicit a: DummyImplicit): A => Unit => A =
    a => { case () => a }

  override def expWhole[A <: Any](implicit a: DummyImplicit): (Unit => A) => A =
    f => f(())

  override def expCompose[A <: Any, B <: Any, C <: Any](implicit a: DummyImplicit, b: DummyImplicit, c: DummyImplicit): (A => B) => (C => A) => C => B =
    f => g => f.compose(g)

  override def homLeftConstraint[A <: Any, B <: Any](hom: A => B): DummyImplicit =
    DummyImplicit.dummyImplicit

  override def homRightConstraint[A <: Any, B <: Any](hom: A => B): DummyImplicit =
    DummyImplicit.dummyImplicit

  override def compose[A <: Any, B <: Any, C <: Any](f: B => C, g: A => B): A => C =
    f.compose(g)

  override implicit def bifunctorApplyConstraint[A <: Any, B <: Any](implicit a: DummyImplicit, b: DummyImplicit): DummyImplicit =
    DummyImplicit.dummyImplicit

  override def bifunctorLeftConstraint[A <: Any, B <: Any](implicit e: DummyImplicit): DummyImplicit =
    DummyImplicit.dummyImplicit

  override def bifunctorRightConstraint[A <: Any, B <: Any](implicit e: DummyImplicit): DummyImplicit =
    DummyImplicit.dummyImplicit

  override def bimap[A <: Any, B <: Any, C <: Any, D <: Any](f: A => C, g: B => D): ((A, B)) => (C, D) =
    { case (a, b) => (f(a), g(b)) }

}

object OptionMonad extends Monad[
  /* Obj = */ Any,
  /* Con = */ λ[α => DummyImplicit],
  /* Hom = */ Function1,
  /* F = */ Option
] {

  override def endofunctorCategory: Category[Any, λ[α => DummyImplicit], Function] = Scal

  override def map[A, B](f: A => B): Option[A] => Option[B] = _.map(f)

  override def pure[A](implicit a: DummyImplicit): A => Option[A] = Option(_)
  override def flatten[A](implicit a: DummyImplicit): Option[Option[A]] => Option[A] = _.flatten

  override implicit def functorApplyConstraint[A <: Any](implicit a: DummyImplicit): DummyImplicit = a
  override def functorExtractConstraint[A <: Any](implicit fa: DummyImplicit): DummyImplicit = fa

}

object ScalInstances {
  trait all {
    implicit val scalCategory: CartesianClosed[Any, λ[α => DummyImplicit], Function1, Unit, Tuple2, Function1] = Scal
    implicit val scalArrow: Arrow[Any, λ[α => DummyImplicit], Function1, Unit, Tuple2, Function1] = Scal
    implicit val optionMonad: Monad[Any, λ[α => DummyImplicit], Function1, Option] = OptionMonad
  }
}
