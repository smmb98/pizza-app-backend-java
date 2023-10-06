package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SizeRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SizeResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.models.Size;
import dev.mohibullah.pizzaappbackendjava.repositories.SizeRepository;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.SizeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImplementation implements SizeServiceInterface {

    private final SizeRepository sizeRepository;

    @Override
    public SizeResponseDTO createSize(SizeRequestDTO sizeRequestDTO) {

        Size size = sizeRepository.save(mapToEntity(sizeRequestDTO));

        return mapToResponseDto(size);
    }

    @Override
    public BaseShowAllResponseDTO<SizeResponseDTO> showSizes(int page, int pageSize) {
        if (pageSize == 0) {
            List<Size> allSizes = sizeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            if (allSizes.isEmpty()) {
                throw new EmptyItemsListException("No Sizes in database");
            }

            List<SizeResponseDTO> content = allSizes.stream()
                    .map(size -> mapToResponseDto(size))
                    .toList();

            BaseShowAllResponseDTO<SizeResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(0);
            response.setPageSize(allSizes.size());
            response.setTotalElements(allSizes.size());
            response.setTotalPages(1);
            response.setLast(true);

            return response;
        } else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));
            Page<Size> sizes = sizeRepository.findAll(pageable);

            if (sizes.getTotalElements() == 0) {
                throw new EmptyItemsListException("No Sizes in database");
            }

            if (sizes.isEmpty()) {
                throw new EmptyItemsListException("No Sizes in page: " + sizes.getNumber());
            }

            List<Size> listOfSize = sizes.getContent();
            List<SizeResponseDTO> content = listOfSize.stream()
                    .map(size -> mapToResponseDto(size))
                    .toList();

            BaseShowAllResponseDTO<SizeResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(sizes.getNumber());
            response.setPageSize(sizes.getSize());
            response.setTotalElements(sizes.getTotalElements());
            response.setTotalPages(sizes.getTotalPages());
            response.setLast(sizes.isLast());

            return response;
        }
    }


    @Override
    public SizeResponseDTO updateSize(SizeRequestDTO sizeRequestDTO, int sizeId) {
        Size size = sizeRepository.findById(sizeId).orElseThrow(() -> new ItemNotFoundException("Size could not be found"));
        sizeRepository.save(mapToEntity(sizeRequestDTO, size));
        return mapToResponseDto(size);
    }


    private SizeResponseDTO mapToResponseDto(Size size) {
        SizeResponseDTO sizeResponseDTO = new SizeResponseDTO();

        sizeResponseDTO.setId(size.getId());
        sizeResponseDTO.setMeasurement(size.getMeasurement());
        sizeResponseDTO.setDescription(size.getDescription());
        sizeResponseDTO.setCreatedAt(size.getCreatedAt());
        sizeResponseDTO.setUpdatedAt(size.getUpdatedAt());

        return sizeResponseDTO;
    }

    private Size mapToEntity(SizeRequestDTO sizeRequestDTO) {
        Size size = new Size();
        size.setMeasurement(sizeRequestDTO.getMeasurement());
        size.setDescription(sizeRequestDTO.getDescription());

        return size;
    }

    private Size mapToEntity(SizeRequestDTO sizeRequestDTO, Size size) {
        size.setMeasurement(sizeRequestDTO.getMeasurement());
        size.setDescription(sizeRequestDTO.getDescription());

        return size;
    }
}
