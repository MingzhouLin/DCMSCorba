package CenterServerOrb.CenterServerPackage;


/**
* CenterServerOrb/CenterServerPackage/except.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CenterServerOrb.idl
* Thursday, June 7, 2018 9:53:52 o'clock PM EDT
*/

public final class except extends org.omg.CORBA.UserException
{
  public String reason = null;

  public except ()
  {
    super(exceptHelper.id());
  } // ctor

  public except (String _reason)
  {
    super(exceptHelper.id());
    reason = _reason;
  } // ctor


  public except (String $reason, String _reason)
  {
    super(exceptHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class except