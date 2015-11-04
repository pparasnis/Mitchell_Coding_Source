package databaseService;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.VehicleInfoType;

public class Test {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			EntityManagerFactory factory = Persistence
					.createEntityManagerFactory("databaseService");
			EntityManager em = factory.createEntityManager();
			List<MitchellClaimType> result = em.createQuery(
					"SELECT p FROM MitchellClaimType p",
					MitchellClaimType.class).getResultList();

			for (MitchellClaimType ls : result) {
				List<VehicleInfoType> iter = new ArrayList<VehicleInfoType>();
				iter.addAll(ls.getVehicles().getVehicleDetails());

				JAXBContext jaxbContext = JAXBContext
						.newInstance(MitchellClaimType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
						true);

				jaxbMarshaller.marshal(ls, System.out);

				for (VehicleInfoType vh : iter) {
					System.out.println(vh.getMakeDescription());
				}
			}

			System.out.println(result);
/*
			File file = new File(
					"C:\\Users\\Priti\\Downloads\\Coding Challenge\\create-claim1.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(MitchellClaimType.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MitchellClaimType claim = (MitchellClaimType) jaxbUnmarshaller
					.unmarshal(file);
			em.getTransaction().begin();
			em.persist(claim);
			em.getTransaction().commit();
			em.close();
			System.out.println(claim);*/
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// C:\Users\Priti\Downloads\Coding Challenge

}
