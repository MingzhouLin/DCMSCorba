package CenterServerOrb;

/**
* CenterServerOrb/CenterServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CenterServerOrb.idl
* Thursday, June 7, 2018 9:53:52 o'clock PM EDT
*/

public final class CenterServerHolder implements org.omg.CORBA.portable.Streamable
{
  public CenterServerOrb.CenterServer value = null;

  public CenterServerHolder ()
  {
  }

  public CenterServerHolder (CenterServerOrb.CenterServer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CenterServerOrb.CenterServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CenterServerOrb.CenterServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CenterServerOrb.CenterServerHelper.type ();
  }

}