import java.util.concurrent.Semaphore;
public class ReaderWriter
{
static int readCount=0;
static Semaphore S= new Semaphore(1);
static Semaphore wrt= new Semaphore(1);
static class Reader implements Runnable
{
public void run()
{
try
{
S.acquire();
readCount++;
if(readCount==1)
wrt.acquire();
S.release();
System.out.println(Thread.currentThread().getName()+"started reading");
Thread.sleep(500);
System.out.println(Thread.currentThread().getName()+"finished reading");
S.acquire()
readCount--;
if(readCount==0)
wrt.release();
S.release();
}
catch(Exception e)
{
System.out.println(e);
}
}
}
static class Writer implements Runnable
{
public void run()
{
try
{
wrt.acquire();
System.out.println(" started writing");
Thread.sleep(500);
System.out.println(" finished writing");
wrt.release();
}
catch(Exception e)
{
System.out.println(e);
}
}
}
 public static void main(String[] args)
   {
    Reader r=new Reader();
	Writer w=new Writer();
	Thread t1=new Thread(r);
	t1.setName("reader1");
	Thread t2=new Thread(r);
	t2.setName("reader2");
	Thread t3=new Thread(w);
	t3.setName("reader3");
	t1.start();
	t2.start();
	t3.start();
   }
}	

	