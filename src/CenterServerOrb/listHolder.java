package CenterServerOrb;


/**
* CenterServerOrb/listHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CenterServerOrb.idl
* Thursday, June 7, 2018 9:53:52 o'clock PM EDT
*/

public final class listHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public listHolder ()
  {
  }

  public listHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CenterServerOrb.listHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CenterServerOrb.listHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CenterServerOrb.listHelper.type ();
  }

}
