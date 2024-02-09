package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.OrderStatusModel;
import com.bootcamp.gestor.models.PurchaseOrderModel;
import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.repositories.AddressRepository;
import com.bootcamp.gestor.repositories.OrderStatusRepository;
import com.bootcamp.gestor.repositories.PurchaseOrderRepository;
import com.bootcamp.gestor.repositories.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepo;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	SupplierRepository supplierRepo;

	@Autowired
	OrderStatusRepository orderStatusRepo;

	public List<PurchaseOrderModel> getAllPurchaseOrders() {
		return purchaseOrderRepo.findAll();
	}

	public PurchaseOrderModel getPurchaseOrderById(int id) {

		return purchaseOrderRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar una orden con el id " + id));
	}

	public PurchaseOrderModel createPurchaseOrder(PurchaseOrderModel purchOrder) {
		Optional<SupplierModel> s = supplierRepo.findById(purchOrder.getSupplier().getSupId());
		Optional<OrderStatusModel> os = orderStatusRepo.findById(purchOrder.getOrdStatus().getOrdstId());

		// cotrolar fechas

		if (!s.isPresent())
			throw new EntityNotFoundException("No se pudo encontrar el proveedor de esta orden");
		if (!os.isPresent())
			throw new EntityNotFoundException("No se pudo encontrar el estado de esta orden");

		SupplierModel supplier = s.get();
		OrderStatusModel status = os.get();

		purchOrder.setSupplier(supplier);
		purchOrder.setOrdStatus(status);
		purchaseOrderRepo.save(purchOrder);
		return purchaseOrderRepo.findById(purchOrder.getOrdId()).get();
	}

	public String updatePurchaseOrder(int id, PurchaseOrderModel purchOrder) {
		Optional<PurchaseOrderModel> o = purchaseOrderRepo.findById(id);
		if (o.isPresent()) {

			
			Optional<OrderStatusModel> os = orderStatusRepo.findById(purchOrder.getOrdStatus().getOrdstId());
//		if(Assert.isNull(purchOrder.getOrdIssueDate(), "")) {
//			validar fechas de inicio y fecha de entrega
//		}


			if (!os.isPresent())
				throw new EntityNotFoundException("No se pudo encontrar el estado de esta orden");


			OrderStatusModel status = os.get();
			purchOrder.setOrdDeliveryInfo(o.get().getOrdDeliveryInfo());

			purchOrder.setOrdStatus(status);
			purchaseOrderRepo.save(purchOrder);
			return "Orden #" + purchOrder.getOrdId() + " modificada exitosamente";
		} else {
			throw new EntityNotFoundException("No se pudo encontrar una orden con el id " + id);

		}
	}

	public String deletePurchaseOrder(int id) {
		Optional<PurchaseOrderModel> o = purchaseOrderRepo.findById(id);
		if (o.isPresent()) {

			PurchaseOrderModel purch= o.get();
			OrderStatusModel status =  orderStatusRepo.findById(10).get();
			purch.setOrdStatus(status);
			purchaseOrderRepo.save(purch);
			return "Orden #" + purch.getOrdId() + " cancelada exitosamente";
		} else {
			throw new EntityNotFoundException("No se pudo encontrar una orden con el id " + id);
		}
	}

	public List<PurchaseOrderModel> getOrderByStatus(int id) {
		Optional<OrderStatusModel> orderStatus= orderStatusRepo.findById(id);
		if(orderStatus.isPresent()) {
			return purchaseOrderRepo.findByordStatus(orderStatus.get());
		}
		else {
			throw new EntityNotFoundException("No se pudo encontrar el estado");
		}
	}
}
