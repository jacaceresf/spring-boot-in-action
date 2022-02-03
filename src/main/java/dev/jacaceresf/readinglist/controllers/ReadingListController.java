package dev.jacaceresf.readinglist.controllers;

import dev.jacaceresf.readinglist.config.AmazonProperties;
import dev.jacaceresf.readinglist.models.Book;
import dev.jacaceresf.readinglist.repositories.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private final ReadingListRepository readingListRepository;
    private final AmazonProperties amazonProperties;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository,
                                 AmazonProperties amazonProperties) {
        this.readingListRepository = readingListRepository;
        this.amazonProperties = amazonProperties;
    }


    @GetMapping("/{reader}")
    public String readerBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> bookByReader = readingListRepository.findBookByReader(reader);

        if (bookByReader != null) {
            model.addAttribute("books", bookByReader);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }

        return "readingList";
    }

    @PostMapping("/{reader}")
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }

}
