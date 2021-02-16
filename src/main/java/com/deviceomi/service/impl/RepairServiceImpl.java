package com.deviceomi.service.impl;

import com.deviceomi.model.*;
import com.deviceomi.payload.request.RepairRequest;
import com.deviceomi.payload.response.DevicePersonResponse;
import com.deviceomi.payload.response.RepairResponse;
import com.deviceomi.repository.DeviceRepository;
import com.deviceomi.repository.HistoryRepository;
import com.deviceomi.repository.RepairRepository;
import com.deviceomi.search.RepairSearch;
import com.deviceomi.service.RepairService;
import com.deviceomi.util.FormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    RepairRepository repairRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    private HistoryRepository historyRepository;


    @Override
    public void saveOrUpdate(RepairRequest repairRequest) {
        if (repairRequest != null){
            HistoryEntity historyEntity = null;
            RepairEntity repairEntity = null;
            if (repairRequest.getId() != null){
                /**
                 * update
                 * */
                repairEntity = repairRepository.findById(repairRequest.getId()).orElse(new RepairEntity());
                Long idDevice = repairEntity.getDeviceRepair().getId();
                if(repairRequest.getIdDevice() != null){
                    DeviceEntity deviceEntity = deviceRepository.findById(repairRequest.getIdDevice()).orElse(new DeviceEntity());
                    deviceEntity.setStatus(repairRequest.getStatus());
                    deviceRepository.save(deviceEntity);
                    repairEntity.setDeviceRepair(deviceEntity);
                    historyEntity=new HistoryEntity("đã tạo mới thông tin mượn trả với mã thiết bị ",
                        deviceEntity.getCodeDevice(),"Quản lý sửa chữa");
                }
                repairEntity = repairRequest.toEntity(repairEntity);
            }else {
                /**
                 * create
                 * */
                repairEntity = repairRequest.toEntity();

                    DeviceEntity deviceEntity = deviceRepository.findById(repairRequest.getIdDevice()).orElse(new DeviceEntity());
                    deviceEntity.setStatus(repairRequest.getStatus());
                    repairEntity.setDeviceRepair(deviceEntity);
                    historyEntity=new HistoryEntity("đã chỉnh sửa thông tin mượn trả với mã thiết bị ",
                            deviceEntity.getCodeDevice(),"Quản lý sửa chữa");

            }
            repairRepository.save(repairEntity);
            if(historyEntity!=null)
                historyRepository.save(historyEntity);
        }
    }

    @Override
    public void delete(Long id) {
        RepairEntity repairEntity = repairRepository.findById(id).orElse(new RepairEntity());
        repairRepository.delete(repairEntity);
        historyRepository.save(new HistoryEntity("đã xóa thông tin mượn trả với mã thiết bị ",
                repairEntity.getDeviceRepair().getCodeDevice(),"Quản lý sửa chữa"));
    }

    @Override
    public Page<RepairEntity> paginationRepair(Pageable pageable) {
        Page<RepairEntity> repairs = repairRepository.findAll(pageable);
        return repairs;
    }

    @Override
    public List<RepairResponse> findAll() {
        List<RepairResponse> lists = new ArrayList<>();
        repairRepository.findAll().stream().map(item -> new RepairResponse(item)).forEach(lists::add);
        return lists;
    }

    @Override
    public List<RepairResponse> search(RepairSearch repairSearch) {
        List<RepairEntity> repairEntities = repairRepository.getRepairSearch(
                repairSearch.getCodeDevice(), repairSearch.getTypeDevice(),
                FormatDate.stringToDate(repairSearch.getFromDateRepair()),FormatDate.stringToDate(repairSearch.getToDateRepair()),
                FormatDate.stringToDate(repairSearch.getFromDateFinish()),FormatDate.stringToDate(repairSearch.getToDateFinish()), repairSearch.getStatus());
            return repairEntities.stream().map(a ->new RepairResponse(a)).collect(Collectors.toList());
    }

    @Override
    public RepairResponse getRepairById(Long id) {
        RepairResponse repairResponse = null;
        if(id != null){
            RepairEntity repairEntity = repairRepository.findById(id).orElse(new RepairEntity());
            if(repairEntity == null)
                return null;
            repairResponse = new RepairResponse(repairEntity);
        }
        return repairResponse;
    }
}
