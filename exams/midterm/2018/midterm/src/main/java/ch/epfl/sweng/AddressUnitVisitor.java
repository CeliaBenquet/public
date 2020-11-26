package ch.epfl.sweng;

public interface AddressUnitVisitor {
    public void visit(CompositeAddressUnit unit);
    public void visit(House house);
}
