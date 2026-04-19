import { render, screen } from '@testing-library/react';
import FetchEmployees from '../FetchEmployees';
import {describe, it, expect, vi, beforeEach} from 'vitest';
import { MemoryRouter, useNavigate } from 'react-router';
import * as router from  'react-router';
import userEvent from '@testing-library/user-event';

describe('FetchEmployees', ()=>{

    const mockEmployees = [
      {
        id: 4,
        firstName: "Kim",
        midName: "Min",
        lastName: "Joon",
        email: "kim.mj@company.com",
        phone: "+61 400 111 111",
        address: "Seoul, South Korea",
        employmentType: "Full-time",
        workType: "permanent",
        hoursPerWeek: 40,
        startDate: "2024-01-10",
        finishDate: null,
        onGoing: true,
    },
  ];
  
  const mockNavigate = vi.fn();

 
 beforeEach(() => {
    vi.clearAllMocks();
    //useNavigate must be spied

    vi.spyOn(router, "useNavigate").mockReturnValue(mockNavigate);

    global.confirm = vi.fn();
    global.fetch = vi.fn();

    // GET request mock (initial load)
    global.fetch.mockResolvedValueOnce({
      json: () => Promise.resolve(mockEmployees),
  
  });
 });
 //test delete - employee exists 
 it('It should delete employee when confirmed', async () =>{
    const mockUser = userEvent.setup();
    global.confirm.mockReturnValue(true);
    global.fetch.mockResolvedValue({ok: true});

    //<MemoryRouter> is added to wrap <FetchEmployees/> because in App.jsx, <BrowserRouter> wraps <FetchEmployees/> 
    render(
    <MemoryRouter>
        <FetchEmployees/>
    </MemoryRouter>
    );

    const removeButtons = await screen.findAllByText("delete");
    await mockUser.click(removeButtons[0]);


    expect(global.fetch).toHaveBeenCalledWith(
    expect.stringContaining("/api/employees"),
    expect.objectContaining({ method: "DELETE" })
    );
 });




it("navigates on edit click", async () => {
  
  const mockUser2 = userEvent.setup();

  render(
    <MemoryRouter>
        <FetchEmployees/>
    </MemoryRouter>);

  const editButtons =  await screen.findAllByText("Edit");
  await mockUser2.click(editButtons[0]);

  expect(mockNavigate).toHaveBeenCalledWith("/employees/4");
    });
});
